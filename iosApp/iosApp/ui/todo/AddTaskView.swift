//
//  AddTaskView.swift
//  iosApp
//
//  Created by Ilya Nikolaev on 07/08/2019.
//

import UIKit
import share

class AddTaskView: UIView , RenderView{
   

    @IBOutlet var contentView: UIView!
    private var presenter: Storage!
    
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
        Bundle.main.loadNibNamed("AddTaskView", owner: self, options: nil)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleWidth,.flexibleHeight]
        addSubview(contentView)
    }
    
    func initialaze(){
        let singleTapGesture = UITapGestureRecognizer(target: self, action: #selector(methodq))
        singleTapGesture.numberOfTapsRequired = 1
        contentView.addGestureRecognizer(singleTapGesture)
    }
    
    @objc func methodq(req:UITapGestureRecognizer) {
        presenter.accept(wish: TodoAddTaskStorage.TodoAddWishCloseAddTaskMenu())
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
    
    
}
