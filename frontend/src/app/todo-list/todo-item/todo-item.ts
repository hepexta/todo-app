import { TodoCategory } from "../todo-category";

export class TodoItem {
    constructor(public id: string, public label: string, public description: string,
        public category: TodoCategory, public isCompleted: boolean, public completedOn?: Date,
        public isHidden?: boolean) {
    }
}
