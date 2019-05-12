public class ObscureTestExample {

    [Test]
    public void MultiplyPositiveByNegativeProducesCorrectResult()
    {
       	 MultiplyTwoNumnbersAndAssertTheCorrectValueAndThatOperationIsValid(-1, 3, -3, true);
    }

    [Test]
    public void MultiplyTwoPositiveNumbersProducesCorrectResult()
    {
         MultiplyTwoNumbersAndAssertTheCorrectValueAndThatOperationIsValid(1, 3, 3, true);
    }
}