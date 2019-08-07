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
    
    func updateData(list : [TodoModel]){
        todoList = list
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 20//todoList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if(indexPath.row == 0){
            let cell = tableView.dequeueReusableCell(withIdentifier: "HeaderTodoCell", for: indexPath) as! HeaderTodoCell
            //let todoModel = todoList[indexPath.row]
            //cell.title.text = "My tasks"todoModel.title
            return cell
        }else{
            let cell = tableView.dequeueReusableCell(withIdentifier: "TodoCell", for: indexPath) as! TodoCell
            return cell
        }
    }
}
