//
//  AddTaskView.swift
//  iosApp
//
//  Created by Ilya Nikolaev on 07/08/2019.
//

import UIKit
import share

class AddTaskView: UIView , RenderView{
   
    @IBOutlet var viewConstraint: NSLayoutConstraint!
    @IBOutlet var menu: UIView!
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
        
        viewConstraint.constant = 100
        self.menu.frame.origin.y = self.menu.frame.height
        
        UIView.animate(withDuration: 0.3, animations: {
            self.menu.frame.origin.y = 0
        }, completion: nil)
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
