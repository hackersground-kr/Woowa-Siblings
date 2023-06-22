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
        AppCenter.start(withAppSecret: "4c5d8acf-ebae-4b3c-936f-84ee1d12b7b0", services: [Distribute.self])
        return true
    }
}
