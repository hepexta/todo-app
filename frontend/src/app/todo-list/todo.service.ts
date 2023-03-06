import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { HandleError, HttpErrorHandler } from "../shared/http-error-handler.service";
import { TodoItem } from "./todo-item/todo-item";
import { TodoCategory } from "./todo-category";
import { catchError, map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { formatDate } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  private handleError: HandleError;
  private categoryBaseUrl = `${environment.apiUrl}/v1/categories;`
  private todoBaseUrl = `${environment.apiUrl}/v1/todos`;

  constructor(private http: HttpClient, httpErrorHandler: HttpErrorHandler) {
    this.handleError = httpErrorHandler.createHandleError('TodoService');
  }

  public getTodoList(): Observable<TodoItem[]> {
    return this.http.get<TodoItem[]>(this.todoBaseUrl)
      .pipe(
        map((todos: any) => {
          let todoList: TodoItem[] = todos.map(this._transform);
          return todoList;
        }),
        catchError(this.handleError('getTodoList', []))
      );
  }

  public getCategoryList(): Observable<TodoCategory[]> {
      return this.http.get<TodoCategory[]>(this.categoryBaseUrl)
        .pipe(
          map((categories: any) => {
            let categoryList: TodoCategory[] = categories.map(this._transformCategory);
            return categoryList;
          }),
          catchError(this.handleError('getCategoryList', []))
        );
    }

  public addTodoItem(todoItem: TodoItem): Observable<TodoItem> {
  console.log("add "+todoItem);
    return this.http
      .post<TodoItem>(`${this.todoBaseUrl}`, {
        id: todoItem.id,
        label: todoItem.label,
        description: todoItem.description,
        categoryId: todoItem.category.categoryId,
        done: this._getDonePropertyResult(todoItem)
      })
      .pipe(
        catchError(this.handleError('addTodoItem', [])),
        map(this._transform));
  }

  public editTodoItem(todoItem: TodoItem): Observable<TodoItem> {
    console.log("update "+todoItem);
    return this.http
      .put<TodoItem>(`${this.todoBaseUrl}/${todoItem.id}`, {
        id: todoItem.id,
        label: todoItem.label,
        description: todoItem.description,
        categoryId: todoItem.category.categoryId,
        status: this._getDonePropertyResult(todoItem) ? 'DONE' : null
      })
      .pipe(
        catchError(this.handleError('editTodoItem', [])),
        map(this._transform));
  }

  public deleteTodoItem(todoId: string): any {
    return this.http
      .delete<TodoItem>(`${this.todoBaseUrl}/${todoId}`)
      .pipe(catchError(this.handleError('deleteTodoItem', [])));
  }

  private _transform(dbTodoItem: any): TodoItem {
    console.log('dbTodoItem = '+dbTodoItem);
    let isCompleted = dbTodoItem.status === 'DONE';

    return new TodoItem(
      dbTodoItem.todoItemId,
      dbTodoItem.label,
      dbTodoItem.description,
      new TodoCategory(dbTodoItem.categoryId, dbTodoItem.categoryName, null),
      isCompleted,
      isCompleted? dbTodoItem.updatedAt:''
    )
  }

  private _transformCategory(dbCategoryItem: any): TodoCategory {
    debugger;
    console.log(dbCategoryItem);
    return new TodoCategory(
      dbCategoryItem.categoryId,
      dbCategoryItem.categoryName,
      dbCategoryItem.description
    )
  }

  private _getDonePropertyResult(todoItem: TodoItem): string | boolean {
    let done: string | boolean;
    if (todoItem.isCompleted) {
      done = formatDate(todoItem.completedOn, 'dd-MM-yyyy', 'en-US');
    } else {
      done = false;
    }

    return done;
  }
}
