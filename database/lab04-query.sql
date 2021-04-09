SELECT c.codins, c.nome, c.crediti, c.pd
FROM corso c, studente s, iscrizione i
WHERE c.codins = i.codins AND s.matricola = i.matricola AND s.matricola = 146101
