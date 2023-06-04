-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id31::hemant
ALTER TABLE public.business_unit DROP COLUMN time_zone;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id31' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id28::hemant
ALTER TABLE public.nomination DROP COLUMN submit_date;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id28' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id26::hemant
ALTER TABLE public.best_practices DROP COLUMN submit_date;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id26' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id23::hemant
ALTER TABLE public.team DROP COLUMN ppt_status;

ALTER TABLE public.team DROP COLUMN ppt_submit_date;

ALTER TABLE public.team DROP COLUMN criteria_status;

ALTER TABLE public.team DROP COLUMN criteria_submit_date;

ALTER TABLE public.team DROP COLUMN visit_report_status;

ALTER TABLE public.team DROP COLUMN visit_report_submit_date;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id23' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id22::hemant
ALTER TABLE public.team DROP COLUMN final_score;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id22' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id21::hemant
ALTER TABLE public.nomination DROP COLUMN type;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id21' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id20::hemant
ALTER TABLE public.best_practice DROP COLUMN email_ids;

ALTER TABLE public.best_practice RENAME COLUMN champions TO champion;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id20' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id19::hemant
ALTER TABLE public.committee_member_rel DROP CONSTRAINT fk_committee_member_rel_business_unit_id;

ALTER TABLE public.committee_member_rel DROP COLUMN business_unit_id;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id19' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id18::hemant
ALTER TABLE public.team DROP COLUMN marking_completed;

ALTER TABLE public.team DROP COLUMN report_completed;

ALTER TABLE public.team DROP COLUMN ppt_uploaded;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id18' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id17::hemant
ALTER TABLE public.team DROP COLUMN review_comp_date;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id17' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id16::hemant
ALTER TABLE public.nomination DROP COLUMN submitted;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id16' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id15::hemant
ALTER TABLE public.team DROP COLUMN prev_visit_date;

ALTER TABLE public.team DROP COLUMN completed;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id15' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id14::hemant
ALTER TABLE public.business_unit ALTER COLUMN  location SET NOT NULL;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id14' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id13::razamd
ALTER TABLE public.marking ALTER COLUMN score TYPE BIGINT USING (score::BIGINT);

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id13' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id13::hemant
ALTER TABLE public.nomination DROP CONSTRAINT fk_nomination_award_id;

DROP TABLE public.nomination;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id13' AND AUTHOR = 'hemant' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id12::razamd
ALTER TABLE public.form_field DROP COLUMN serial_no;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id12' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id11::razamd
ALTER TABLE public.hall_of_fame DROP CONSTRAINT fk_hall_of_fame_award_id;

DROP TABLE public.hall_of_fame;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id11' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id10::razamd
ALTER TABLE public.visit_report DROP CONSTRAINT fk_visit_report_committee_id;

ALTER TABLE public.visit_report DROP CONSTRAINT fk_visit_report_team_id;

DROP TABLE public.visit_report;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id10' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id9::razamd
ALTER TABLE public.best_practice DROP CONSTRAINT fk_best_practice_committee_id;

DROP TABLE public.best_practice;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id9' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id8::razamd
ALTER TABLE public.marking DROP CONSTRAINT fk_marking_form_field_id;

ALTER TABLE public.marking DROP CONSTRAINT fk_marking_team_id;

DROP TABLE public.marking;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id8' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id7::razamd
ALTER TABLE public.team DROP CONSTRAINT fk_team_business_unit_id;

ALTER TABLE public.team DROP CONSTRAINT fk_team_award_id;

DROP TABLE public.team;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id7' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id6::razamd
ALTER TABLE public.committee_member_rel DROP CONSTRAINT fk_committee_member_rel_member_id;

ALTER TABLE public.committee_member_rel DROP CONSTRAINT fk_committee_member_rel_committee_id;

DROP TABLE public.committee_member_rel;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id6' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id5::razamd
ALTER TABLE public.committee DROP CONSTRAINT fk_committee_award_id;

DROP TABLE public.committee;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id5' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id4::razamd
ALTER TABLE public.form_field DROP CONSTRAINT fk_form_field_parent_id;

ALTER TABLE public.form_field DROP CONSTRAINT fk_form_field_award_id;

DROP TABLE public.form_field;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id4' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id3::razamd
DROP INDEX public.uk_award_name;

DROP TABLE public.award;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id3' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id2::razamd
ALTER TABLE public.users DROP CONSTRAINT fk_users_business_unit_id;

DROP INDEX public.uk_users_email;

DROP INDEX public.uk_users_username;

DROP TABLE public.users;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id2' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-1.0.x.xml::v1.0.x-id1::razamd
DROP INDEX public.uk_business_unit_name;

DROP TABLE public.business_unit;

DELETE FROM public.databasechangelog WHERE ID = 'v1.0.x-id1' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-1.0.x.xml';
