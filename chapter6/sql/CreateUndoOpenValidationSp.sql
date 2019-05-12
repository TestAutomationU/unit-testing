CREATE PROCEDURE [dbo].[ValidateUndoOpen]
        @PayGroup char(6) = NULL,
        @PerControl char(9) = NULL
AS

/**
 * A validation helper for payroll undo functionality.
 */

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
BEGIN

    SET NOCOUNT ON

    SELECT
        CASE WHEN 
        (
            SELECT TOP 1 MbtPayGroup
            FROM dbo.M_Batch  WITH (NOLOCK) 
            WHERE MbtPayGroup = @PayGroup AND 
				  MbtPerControl = @PerControl AND 
				  (MbtPrinted = 'Y' OR MbtPostOnly = 'Y')
        ) 
        IS NULL THEN 0 ELSE 1 END AS HasPrintedPostOnlyCheck,
        
        CASE WHEN 
        (
            SELECT TOP 1 PrgPayGroup
            FROM PayReg 
            WHERE PrgPayGroup = @PayGroup AND 
				  PrgPerControl = @PerControl AND 
				  PrgRecordCreationSource <> 'E'
        ) 
        IS NULL THEN 0 ELSE 1 END AS HasPostCheckHistory
END
