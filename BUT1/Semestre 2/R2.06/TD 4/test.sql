use bd_r206;

SELECT AVG(nombreQualifications) AS moyenneQualifications
FROM (
    SELECT COUNT(unPilote) AS nombreQualifications
    FROM Qualification
    GROUP BY unPilote
) AS subquery;

/*
# moyenneQualifications
'2.0000'
*/



