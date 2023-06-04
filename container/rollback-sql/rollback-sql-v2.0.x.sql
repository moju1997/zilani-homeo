-- Rolling Back ChangeSet: db/changelog/changelog-2.0.x.xml::v2.0.x-id9::mojahid
ALTER TABLE public.team DROP COLUMN attendees;

ALTER TABLE public.team DROP COLUMN join_url;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0.x-id9' AND AUTHOR = 'mojahid' AND FILENAME = 'db/changelog/changelog-2.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-2.0.x.xml::v2.0.x-id8::razamd
ALTER TABLE public.team DROP COLUMN duration;

ALTER TABLE public.team DROP COLUMN meeting_id;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0.x-id8' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-2.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-2.0.x.xml::v2.0.x-id6::razamd
DELETE FROM public.databasechangelog WHERE ID = 'v2.0.x-id6' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-2.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-2.0.x.xml::v2.0.x-id5::razamd
ALTER TABLE public.visit_report RENAME COLUMN recommendations TO suggestions;

ALTER TABLE public.visit_report RENAME COLUMN improvements TO low_lights;

ALTER TABLE public.visit_report DROP COLUMN remarks;

ALTER TABLE public.visit_report DROP COLUMN factory;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0.x-id5' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-2.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-2.0.x.xml::v2.0.x-id4::razamd
ALTER TABLE public.team DROP COLUMN final_chair_score;

ALTER TABLE public.team DROP COLUMN criteria_chair_submit_date;

ALTER TABLE public.team DROP COLUMN criteria_chair_status;

ALTER TABLE public.team RENAME COLUMN criteria_self_submit_date TO criteria_submit_date;

ALTER TABLE public.team RENAME COLUMN criteria_self_status TO criteria_status;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0.x-id4' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-2.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-2.0.x.xml::v2.0.x-id3::razamd
ALTER TABLE public.marking DROP COLUMN chair_score;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0.x-id3' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-2.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-2.0.x.xml::v2.0.x-id2::razamd
ALTER TABLE public.award DROP COLUMN platform;

ALTER TABLE public.award DROP COLUMN objectives;

ALTER TABLE public.award DROP COLUMN process;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0.x-id2' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-2.0.x.xml';

-- Rolling Back ChangeSet: db/changelog/changelog-2.0.x.xml::v2.0.x-id1::razamd
ALTER TABLE public.user_business_unit_rel DROP CONSTRAINT fk_user_business_unit_rel_user_id;

ALTER TABLE public.user_business_unit_rel DROP CONSTRAINT fk_user_business_unit_rel_business_unit_id;

DROP TABLE public.user_business_unit_rel;

ALTER TABLE public.users DROP COLUMN users;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0.x-id1' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/changelog-2.0.x.xml';
