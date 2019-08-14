//
//  TasksAdapter.swift
//  iosApp
//
//  Created by Ilya Nikolaev on 31/07/2019.
//

import Foundation
import UIKit
import share

class TasksAdapter : NSObject, UITableViewDataSource{
    
    var todoList : [TodoModel] = []
    var presenter: Storage? = nil
    
    func updateData(list : [TodoModel]){
        todoList = list
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return todoList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let index = indexPath.row
        let model = todoList[index]
        
        if(model is TodoModel.TodoModelHeader){
            let headerModel = model as! TodoModel.TodoModelHeader
            let cell = tableView.dequeueReusableCell(withIdentifier: "HeaderTodoCell", for: indexPath) as! HeaderTodoCell
            cell.title.text = headerModel.title
            return cell
        }else{
            let cell = tableView.dequeueReusableCell(withIdentifier: "TodoCell", for: indexPath) as! TodoCell
           
            if(model is TodoModel.TodoModelNote){
                let noteModel = model as! TodoModel.TodoModelNote
                cell.title.text = noteModel.title
                cell.desc.text = noteModel.text
            }
            
            return cell
        }
    }
}
