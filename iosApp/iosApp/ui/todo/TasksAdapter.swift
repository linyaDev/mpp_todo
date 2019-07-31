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
    
    func updateData(){
        
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell  = TaskCell()
        cell.backgroundColor = UIColor.red
        return cell
    }
}
