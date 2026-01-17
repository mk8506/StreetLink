CREATE DATABASE streetlink;
USE streetlink;
CREATE TABLE area_ratings (
                              zipcode INT Primary Key,
                              safety FLOAT,
                              affordability FLOAT,
                              publicEdu FLOAT,
                              traits TEXT,
                              description VARCHAR(255)
);

CREATE TABLE area_review (
                             id INT PRIMARY KEY,
                             zipcode INT,
                             safety INT,
                             affordability INT,
                             publicEdu INT,
                             FOREIGN KEY(zipcode) REFERENCES area_ratings(zipcode)
)

ALTER TABLE area_ratings
    ADD COLUMN city VARCHAR(100),
    ADD COLUMN population INT;
DELIMITER //
DROP PROCEDURE IF EXISTS calculate_and_update_ratings//
CREATE PROCEDURE calculate_and_update_ratings(
    IN p_zipcode INT
)
BEGIN
    SELECT
        AVG(safety),
        AVG(affordability),
        AVG(publicEdu)
    INTO
        @avg_safety,
        @avg_affordability,
        @avg_publicEdu
    FROM area_review
    WHERE zipcode = p_zipcode;
    UPDATE area_ratings
    SET
        safety = @avg_safety,
        affordability = @avg_affordability,
        publicEdu = @avg_publicEdu
    WHERE zipcode = p_zipcode;
END//
DELIMITER ;
TRUNCATE TABLE area_review;
TRUNCATE TABLE area_ratings;
INSERT INTO area_ratings (zipcode, safety, affordability, publicEdu, traits, description, city, population) VALUES
                                                                                                                                       (85281, 0.0, 0.0, 0.0, '["College Town", "High Traffic"]', 'A vibrant area near a large university.', 'Tempe', 50000),
                                                                                                                                       (90210, 0.0, 0.0, 0.0, '["Affluent", "Exclusive", "Coastal", "Proximity"]', 'Known for luxury homes and high-end services.', 'Beverly Hills', 35000);
INSERT INTO area_review (id, zipcode, safety, affordability, publicEdu) VALUES
                                                                                                   (1, 85281, 4, 3, 5),
                                                                                                   (2, 85281, 2, 5, 4),
                                                                                                   (3, 85281, 5, 2, 4),
                                                                                                   (4, 90210, 5, 1, 5),
                                                                                                   (5, 90210, 4, 1, 5);
CALL calculate_and_update_ratings(85281);
CALL calculate_and_update_ratings(90210);
SELECT * FROM area_ratings;
