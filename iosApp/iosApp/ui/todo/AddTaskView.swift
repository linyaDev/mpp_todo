//
//  AddTaskView.swift
//  iosApp
//
//  Created by Ilya Nikolaev on 07/08/2019.
//

import UIKit
import share

class AddTaskView: UIView , RenderView{
   
    @IBOutlet var bottomConstraint: NSLayoutConstraint!
    @IBOutlet var menu: UIView!
    @IBOutlet var contentView: UIView!
    private var presenter: Storage!
    @IBOutlet var title: UITextField!
    @IBOutlet var taskDescription: UITextField!

    
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
        let singleTapGesture = UITapGestureRecognizer(target: self, action: #selector(closeMenu))
        singleTapGesture.numberOfTapsRequired = 1
        contentView.addGestureRecognizer(singleTapGesture)
    
        self.menu.frame.origin.y = self.menu.frame.height
        UIView.animate(withDuration: 0.3, animations: {
            self.menu.frame.origin.y = 0
        }, completion: nil)
       
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillShow(notification:)), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillHide(notification:)), name: NSNotification.Name.UIKeyboardWillHide, object: nil)
    }
    
    @objc func keyboardWillShow(notification: NSNotification) {
       let keyboardSize = (notification.userInfo![UIKeyboardFrameEndUserInfoKey] as! NSValue).cgRectValue.size
        UIView.animate(withDuration: 0.3, animations: {
            self.menu.frame.origin.y =  self.frame.height - keyboardSize.height - self.menu.frame.height
        }, completion: nil)
    }
    
    @objc func keyboardWillHide(notification: NSNotification) {
    
    }
    
    
    @objc func closeMenu(req:UITapGestureRecognizer) {
        presenter.accept(wish: TodoAddTaskStorage.TodoAddWishCloseAddTaskMenu())
    }
    
    @IBAction func sendTodo(_ sender: Any) {
        //let model = TodoModel.init
        
        
        //presenter.accept(wish: TodoAddTaskStorage.TodoAddWishAddTask(todo: model))
    }
    
    func render(state: Any) {
        
    }
    
    func setupPresenter(presenter: Storage) {
        self.presenter = presenter
    }
    
    func haveRemoveAnimation() -> Bool {
        return true
    }
    
    func removeFromParentViewAnimated(listener: RenderViewAnimationListener) {
       
        let completion = { (finished: Bool) in
            listener.animationEnded()
        }
        
        UIView.animate(withDuration: 0.3, animations: {
             self.menu.frame.origin.y += self.menu.frame.height
        }, completion: completion)
        
        //
    }
}
