CREATE TABLE TodoListElement (

    ID SERIAL PRIMARY KEY,
    Description TEXT NOT NULL,
    Status VARCHAR(50) NOT NULL,
    TodoListID INTEGER NOT NULL,
    FOREIGN KEY (TodoListID) REFERENCES TodoList(ID)

);