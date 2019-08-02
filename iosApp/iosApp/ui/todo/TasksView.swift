//
//  TasksView.swift
//  iosApp
//
//  Created by lin on 28/07/2019.
//

import UIKit
import share

class TasksView: UIView /*, TodoStorageView*/{

    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var addTodoButton: UIButton!
    @IBOutlet weak var menuButton: UIButton!
    @IBOutlet var contentView : UIView!
    @IBOutlet var menuView: UIView!
    

    private var presenter: Storage!
    private let tasksAdapter = TasksAdapter()
    
    @IBAction func menuPressed(_ sender: Any) {
        presenter.accept(wish: TodoStorage.TodoWishAddTodo())
    }
    
    @IBAction func todoPressed(_ sender: Any) {
        //presenter.accept(wish: TodoStorage.TodoWishLogin())
    }
    
    /*
    func render(state: Any?) {
        let model  = state as! TodoStorage.TodoState
        tasksAdapter.updateData(list: model.todoList)
        tableView.reloadData()
    }
    
    func getStorageView() -> Screen {
        return Screen(renderView: self, storageViewType: Screen.ScreenType.login)
    }
    
    func setupPresenter(presenter: Storage) {
    
        self.presenter  = presenter
    }*/
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        initialaze()
    }
    
    required init?(coder aCoder: NSCoder) {
        super.init(coder: aCoder)
        initialaze()
    }
    
    func initialaze(){
        setupSelf()
        setupTableView()
        drawShadow()
    }
    
    private func setupSelf(){
        Bundle.main.loadNibNamed("TasksView", owner: self, options: nil)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleWidth,.flexibleHeight]
        addSubview(contentView)
    }
    
    private func setupTableView(){
        tableView.dataSource = tasksAdapter
        tableView.separatorStyle = UITableViewCellSeparatorStyle.none
        let todoCellNib = UINib.init(nibName: "TodoCell", bundle: nil)
        
        self.tableView.register(todoCellNib, forCellReuseIdentifier: "TodoCell")
    }
    
    private func drawShadow(){
        let shadowSize : CGFloat = 5.0
        let shadowPath = UIBezierPath(rect: CGRect(x: 0,
                                                   y: -shadowSize / 2,
                                                   width: menuView.frame.size.width ,
                                                   height: menuView.frame.size.height - shadowSize
        ))
        
        menuView.layer.masksToBounds = false
        menuView.layer.shadowColor = UIColor.black.cgColor
        menuView.layer.shadowOffset = CGSize(width: 0.0, height: 0.0)
        menuView.layer.shadowOpacity = 0.5
        menuView.layer.shadowPath = shadowPath.cgPath
    }
    
}
