//
//  TasksView.swift
//  iosApp
//
//  Created by lin on 28/07/2019.
//

import UIKit
import share

class TasksView: UIView , RenderView{
   
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var addTodoButton: UIButton!
    @IBOutlet weak var menuButton: UIButton!
    @IBOutlet var contentView : UIView!
    @IBOutlet var menuView: UIView!
    

    private var presenter: Storage!
    private let tasksAdapter = TasksAdapter()
    
    @IBAction func menuPressed(_ sender: Any) {
        //presenter.accept(wish: TodoStorage.TodoWishAddTodo())
    }
    
    @IBAction func todoPressed(_ sender: Any) {
        presenter.accept(wish: TodoMainStorage.TodoWishShowAddTask())
    }
    
    func render(state: Any) {
        
    }
    
    func setupPresenter(presenter: Storage) {
        self.presenter = presenter
    }
    
    func haveRemoveAnimation() -> Bool {
        return false
    }
    
    func removeFromParentViewAnimated(listener: RenderViewAnimationListener) {
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
        initNib()
        initialaze()
    }
    
    required init?(coder aCoder: NSCoder) {
        super.init(coder: aCoder)
        initNib()
        initialaze()
    }
    
    private func initNib(){
        Bundle.main.loadNibNamed("TasksView", owner: self, options: nil)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleWidth,.flexibleHeight]
        addSubview(contentView)
    }
    
    func initialaze(){
        setupTableView()
        drawShadow()
    }
    
    
    private func setupTableView(){
        tableView.dataSource = tasksAdapter
        tableView.separatorStyle = UITableViewCellSeparatorStyle.none
        tableView.estimatedRowHeight = 40
        tableView.rowHeight = UITableViewAutomaticDimension
        
        let todoCellNib = UINib.init(nibName: "TodoCell", bundle: nil)
        let headerTodoCellNib = UINib.init(nibName: "HeaderTodoCell", bundle: nil)
        
        self.tableView.register(todoCellNib, forCellReuseIdentifier: "TodoCell")
        self.tableView.register(headerTodoCellNib, forCellReuseIdentifier: "HeaderTodoCell")
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
