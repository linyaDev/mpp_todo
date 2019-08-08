//
//  AppDelegate.swift
//  iosApp
//
//  Created by jetbrains on 12/04/2018.
//  Copyright © 2018 JetBrains. All rights reserved.
//

import UIKit
import share

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate, ViewCreator, ViewHolder, OSDependencies{
  
   
    var window: UIWindow?
    var router: RootRouter?
    var rootViewController: UIViewController!
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool{
        
        window = UIWindow(frame: UIScreen.main.bounds)
        
        rootViewController = ViewController()
        window?.rootViewController = rootViewController
        
        let router = RootRouter()
        self.router = router
        router.activate(dependencies: self)/**/
        window?.makeKeyAndVisible()
        
        //debug
        //let taskView =
        //rootViewController?.view.addSubview(taskView)
        return true
    }
    
    func createView(viewType: ViewType) -> RenderView? {
       
        if viewType is TodoViewTypes {
            let type = viewType as! TodoViewTypes
            switch type {
            case TodoViewTypes.todotable:
                return TasksView(frame: rootViewController!.view.bounds)
            case TodoViewTypes.todoaddtask:
                return AddTaskView(frame: rootViewController!.view.bounds)
            default:
                break
            }
        }
        
        /*
        TodoViewTypes.TodoTable ->  TodoMainView(this)
        TodoViewTypes.TodoMenu -> TodoTasksMenu(this)
        TodoViewTypes.TodoAddTask -> TodoAddTask(this)*/
        //viewType.
       
        return nil
    }
    
    func addView(renderView: RenderView) {
        if (renderView is UIView) {
            rootViewController?.view.addSubview(renderView as! UIView)
        }
    }
    
    func removeView(renderView: RenderView) {
        if (renderView is UIView) {
           let view = renderView as! UIView
           view.removeFromSuperview()
        }
    }
    
    func removeAnimated(renderView: RenderView) {
        if(renderView.haveRemoveAnimation()){
            renderView.removeFromParentViewAnimated(listener: <#T##RenderViewAnimationListener#>)
        }
       
    }
    
    func viewCreator() -> ViewCreator {
       return self
    }
    
    func viewHolder() -> ViewHolder {
       return self
    }
    
    /*
    func createView(screenType: Screen.ScreenType) -> Screen {
        return  Screen(renderView: TasksView(frame: rootViewController!.view.bounds), storageViewType: Screen.ScreenType.login)
    }
    
    func viewCreator() -> ViewCreator {
        return self
    }
    
    func viewHolder() -> ViewHolder {
        return self
    }
    
    func addView(storageView: Screen){
        let view = storageView.renderView
     
    }
    
    func removeView(storageView: Screen) {
        let view = storageView.renderView
        if (view is UIView) {
            rootViewController?.view.willRemoveSubview((view as! UIView))
        }
    }*/
    
    
    /*
    
    func replaceView(previewScreen: Screen, nextScreen: Screen) {
        
    }*/
    
    func applicationWillResignActive(_ application: UIApplication) {
        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
    }

    func applicationDidEnterBackground(_ application: UIApplication) {
        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }

    func applicationWillEnterForeground(_ application: UIApplication) {
        // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
    }

    func applicationWillTerminate(_ application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    }


}

