/**
 * A validation helper for performance review rating scales.
 */
function RatingScaleLevelsValidator() {
    this.validate = function (levels) {
        this.validateAllLevelsHaveRating(levels);
        this.validateRangesAreContiguous(levels);
    };

    /**
    * Validate that all rating levels have an associated rating code.
    */
    this.validateAllLevelsHaveRating = function (levels) {
        // Iterate through each level in the levels collection passed in.
        $(levels).each(function () {

            // Retrieve this level's rating code.
            if (this.getRating() === "") {
                this.markRatingWithError();
            }
        });
    };

    /**
    * Validate that all rating levels are contiguous.
    */
    this.validateRangesAreContiguous = function (levels) {
        log.clearMessageNow(lstrRangeMustBeContiguous);

        for (i = 1; i < levels.length; i++) {
            var previousLevel = levels[i - 1];
            var currentLevel = levels[i];
            var previousMaxToCurrentMinDelta =
				currentLevel.getMinimumRange() - previousLevel.getMaximumRange();

            if (previousMaxToCurrentMinDelta.toFixed(2) != 0.01) {
                log.add(lstrRangeMustBeContiguous);
                previousLevel.markMaximumRangeWithError();
                currentLevel.markMinimumRangeWithError();
            }
        }
    };
}

module.exports = RatingScaleLevelsValidator;