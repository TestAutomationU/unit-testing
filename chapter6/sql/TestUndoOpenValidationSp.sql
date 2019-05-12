-- Create a new suite of tests.
EXEC tSQLt.NewTestClass 'testValidateUndoOpen';
GO

-- Execute the suite of tests.
EXEC tSQLt.Run 'testValidateUndoOpen';
 
-- Execute all tests.
tSQLt.RunAll
     
-- Create a new test belonging to the `testValidateUndoOpen` suite.
CREATE PROCEDURE testValidateUndoOpen.[test paygroup has printed check within a specified pay period]
AS
BEGIN

    /**
     * A payroll may not be un-opened if there is a printed post-only check.
     * Scenario:
     * 1. Given an existing payroll session that has been previously opened
     * 2. And a printed post-only check has been processed as part of the session
     * 3. When I invoke the payroll undo open validation
     * 4. Then a printed post-only check is found and reported by the validation procedure
     */

    IF OBJECT_ID('actual') IS NOT NULL DROP TABLE actual;
    IF OBJECT_ID('expected') IS NOT NULL DROP TABLE expected;
     
    -- Arrange.
    EXEC tSQLt.FakeTable 'dbo.M_Batch'
    INSERT INTO dbo.M_Batch(MbtPayGroup, MbtPerControl, MbtPrinted, MbtPostOnly) VALUES ('ABC', '201502251', 'Y', 'N')
    CREATE TABLE actual (HasPrintedPostOnlyCheck bit, HasPostCheckHistory bit)
    CREATE TABLE expected (HasPrintedPostOnlyCheck bit, HasPostCheckHistory bit)
    
    -- Act.
    INSERT actual EXEC dbo.ValidateUndoOpen 'ABC', '201502251'
    
    -- Assert.
    INSERT expected (HasPrintedPostOnlyCheck, HasPostCheckHistory) VALUES (1, 0)
    EXEC tSQLt.AssertEqualsTable 'expected', 'actual';
 
END
GO