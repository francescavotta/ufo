SELECT c.codins, c.nome, c.pd, COUNT(*) as tot
FROM corso c, iscrizione i
WHERE c.codins = i.codins AND c.pd=1
GROUP BY c.codins, c.nome, c.pd
