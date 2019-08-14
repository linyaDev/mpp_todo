//
//  TasksView.swift
//  iosApp
//
//  Created by lin on 28/07/2019.
//

import UIKit
import share
import Material
import MaterialComponents.MaterialBottomAppBar
import MaterialComponents.MaterialBottomAppBar_ColorThemer
import MaterialComponents.MaterialButtons_ButtonThemer

class TasksView: UIView , RenderView{
   
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet var contentView : UIView!
    @IBOutlet var menuView: UIView!
    
    private var presenter: Storage!
    private let tasksAdapter = TasksAdapter()
    private let bottomBarView = MDCBottomAppBarView()

    func render(state: Any) {
        let model  = state as! TodoMainStorage.TodoState
        tasksAdapter.updateData(list: model.todoList)
        tableView.reloadData()
    }
    
    func setupPresenter(presenter: Storage) {
        self.presenter = presenter
        tasksAdapter.presenter = presenter
    }
    
    func haveRemoveAnimation() -> Bool {
        return false
    }
    
    func removeFromParentViewAnimated(listener: RenderViewAnimationListener) {
    }
    
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
      
        bottomBarView.translatesAutoresizingMaskIntoConstraints = false
        bottomBarView.autoresizingMask = [.flexibleWidth, .flexibleTopMargin]
        self.addSubview(bottomBarView)
        
        bottomBarView.floatingButton.addTarget(self, action: #selector(addTask), for: .touchUpInside)
    
        bottomBarView.floatingButton.setImage(Icon.add, for: .normal)
        bottomBarView.floatingButtonPosition = .center
        
        DispatchQueue.main.async {
            self.layoutBottomAppBar()
        }
        
        let colorScheme = MDCSemanticColorScheme(defaults: .material201804)
        let buttonScheme = MDCButtonScheme()
        buttonScheme.colorScheme = colorScheme
        MDCFloatingActionButtonThemer.applyScheme(buttonScheme, to: bottomBarView.floatingButton)
        MDCBottomAppBarColorThemer.applySurfaceVariant(withSemanticColorScheme: colorScheme,
                                                       to: bottomBarView)
    }
    
    @objc func addTask(){
        presenter.accept(wish: TodoMainStorage.TodoWishShowAddTask())
    }
    
    private func layoutBottomAppBar() {
        let size = bottomBarView.sizeThatFits(self.bounds.size)
        let bottomBarViewFrame = CGRect(x: 0,
                                        y: self.bounds.size.height - size.height,
                                        width: size.width,
                                        height: size.height)
        bottomBarView.frame = bottomBarViewFrame
    }
    
    func initialaze(){
        setupTableView()
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
    
}
