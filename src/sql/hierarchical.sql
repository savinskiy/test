SELECT s.person_id as id, s.name as name, r.name as cur_rank, m.name as MILITARY_UNIT
FROM staff s
JOIN ranks r on s.rank_id=r.rank_id
JOIN military_units m on m.unit_id=s.unit_id
where CONNECT_BY_ISLEAF=1
START WITH s.name='vasiliev'
CONNECT BY PRIOR person_id = chief
/