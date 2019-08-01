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
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        let router = RootBuilder().build()
        window = UIWindow(frame: UIScreen.main.bounds)
        
        rootViewController = ViewController()
        window?.rootViewController = rootViewController
        self.router = router
    
        router.activate(dependencies: self)
        window?.makeKeyAndVisible()
        
        return true
    }
    
    func createView(screenType: Screen.ScreenType) -> Screen {
        return  Screen(renderView: TasksView(frame: rootViewController!.view.bounds), screenType: Screen.ScreenType.login)
    }
    
    func viewCreator() -> ViewCreator {
        return self
    }
    
    func viewHolder() -> ViewHolder {
        return self
    }
    
    func addView(screen: Screen){
        let view = screen.renderView
        if (view is UIView) {
            rootViewController?.view.addSubview(view as! UIView)
        }
    }
    
    func removeView(screen: Screen) {
        let view = screen.renderView
        if (view is UIView) {
            rootViewController?.view.willRemoveSubview((view as! UIView))
        }
    }
    
    
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

