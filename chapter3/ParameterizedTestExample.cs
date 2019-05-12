public class ParameterizedTestExample {

    [TestCase(-1, 3, 3, Description = "Positive Times Negative")]
    [TestCase(1, 3, 3, Description = "Two Positives")]
    public void MultiplyingTwoNumbersProducesCorrectResult(int first, int second, int expected)
    {
       	 var calculator = new Calculator();
	 var result = calculator.Multiply(first, second);
         result.Should().Be(expected);
    }
}