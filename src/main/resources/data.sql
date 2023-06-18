INSERT INTO task_folders (id, name, task_folder_id) VALUES
  (1, 'To-Do 1', null),
  (2, 'To-Do 2', 1),
  (3, 'To-Do 3', 2),
  (4, 'To-Do 4', 3),
  (5, 'To-Do 5', 1),
  (6, 'To-Do 6', 2);

INSERT INTO tasks (id, task_name, completed, task_folder_id) VALUES
		(1, 'Task 1 - Folder 1', false, 1),
		(2, 'Task 1 - Folder 2', true, 1),
		(3, 'Task 2 - Folder 2', false, 2),
		(4, 'Task 1 - Folder 3', false, 3),
		(5, 'Task 2 - Folder 3', false, 3),
		(6, 'Task 3 - Folder 3', false, 3),
		(7, 'Task 1 - Folder 4', false, 4),
		(8, 'Task 1 - Folder 5', false, 5),
		(9, 'Task 1 - Folder 6', false, 6),
		(10, 'Task 2 - Folder 6', false, 6);
