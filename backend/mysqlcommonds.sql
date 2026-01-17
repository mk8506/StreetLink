CREATE DATABASE project_db;
CREATE TABLE area_ratings (
                              area_code INT Primary Key,
                              safety_rating FLOAT,
                              affordability_rating FLOAT,
                              education_rating FLOAT,
                              traits TEXT,
                              description VARCHAR(255)
);

CREATE TABLE area_review (
                             id INT PRIMARY KEY,
                             area_code INT,
                             safety_rating INT,
                             affordability_rating INT,
                             education_rating INT,
                             FOREIGN KEY(area_code) REFERENCES area_ratings(area_code)
)

USE project_db;
ALTER TABLE area_ratings
    ADD COLUMN city VARCHAR(100),
    ADD COLUMN population INT;
DELIMITER //
DROP PROCEDURE IF EXISTS calculate_and_update_ratings//
CREATE PROCEDURE calculate_and_update_ratings(
    IN p_area_code INT
)
BEGIN
    SELECT
        AVG(safety_rating),
        AVG(affordability_rating),
        AVG(education_rating)
    INTO
        @avg_safety,
        @avg_affordability,
        @avg_education
    FROM area_review
    WHERE area_code = p_area_code;
    UPDATE area_ratings
    SET
        safety_rating = @avg_safety,
        affordability_rating = @avg_affordability,
        education_rating = @avg_education
    WHERE area_code = p_area_code;
END//
DELIMITER ;
TRUNCATE TABLE area_review;
TRUNCATE TABLE area_ratings;
INSERT INTO area_ratings (area_code, safety_rating, affordability_rating, education_rating, traits, description, city, population) VALUES
                                                                                                                                       (85281, 0.0, 0.0, 0.0, 'College Town, High Traffic', 'A vibrant area near a large university.', 'Tempe', 50000),
                                                                                                                                       (90210, 0.0, 0.0, 0.0, 'Affluent, Exclusive, Coastal Proximity', 'Known for luxury homes and high-end services.', 'Beverly Hills', 35000);
INSERT INTO area_review (id, area_code, safety_rating, affordability_rating, education_rating) VALUES
                                                                                                   (1, 85281, 4, 3, 5),
                                                                                                   (2, 85281, 2, 5, 4),
                                                                                                   (3, 85281, 5, 2, 4),
                                                                                                   (4, 90210, 5, 1, 5),
                                                                                                   (5, 90210, 4, 1, 5);
CALL calculate_and_update_ratings(85281);
CALL calculate_and_update_ratings(90210);
SELECT * FROM area_ratings;
