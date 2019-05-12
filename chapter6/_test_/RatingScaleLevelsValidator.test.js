import RatingScaleLevelsValidator from '../lib/RatingScaleLevelsValidator';

describe("Validate all levels have ratings", function(){
    var validator;
    
    var createLevel = function(ratingCode){
        var level = new Object();
        level.getRating = level.markRatingWithError = function() { }
        spyOn(level, "getRating").and.returnValue(ratingCode);
        spyOn(level, "markRatingWithError");
        return level;
    }

    beforeEach(function(){
        validator = new RatingScaleLevelsValidator();
    })

    /**
     * A single level with an assigned rating code should be considered valid.
     * Scenario:
     * 1. Given a set of rating levels with a single rating level
     * 2. And the rating level is assigned with a rating code of "ABC"
     * 3. When I validate the rating levels
     * 4. Then the rating level is considered valid and is not marked with an error
     */
    it("Single level with defined rating should be valid", function(){
        // Arrange.
        var levels = []
        var level = createLevel("ABC");
        levels.push(level);

        // Act.
        validator.validateAllLevelsHaveRating(levels);

        // Assert.
        expect(level.getRating).toHaveBeenCalled();
        expect(level.markRatingWithError).not.toHaveBeenCalled();
    });

    /**
     * A single level with an empty rating code assignment should be considered invalid.
     * Scenario:
     * 1. Given a set of rating levels with a single rating level
     * 2. And the rating level is assigned with a rating code of ""
     * 3. When I validate the rating levels
     * 4. Then the rating level is considered invalid and is marked with an error
     */
    it("Single level with empty rating should be invalid", function(){
        // Arrange.
        var levels = []
        var level = createLevel("");
        levels.push(level);
         
        // Act.
        validator.validateAllLevelsHaveRating(levels);

        // Assert.
        expect(level.getRating).toHaveBeenCalled();
        expect(level.markRatingWithError).toHaveBeenCalled();
    });
});