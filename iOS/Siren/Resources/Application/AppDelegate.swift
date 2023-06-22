//
//  AppDelegate.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import UIKit
import AppCenter
import AppCenterDistribute

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        AppCenter.start(withAppSecret: "7c942c3c-92b7-4797-a8cc-df21747ff9a6", services: [Distribute.self])
        return true
    }
}
