//
//  AppMain.swift
//  Siren
//
//  Created by Mercen on 2023/06/21.
//

import SwiftUI

@main
struct AppMain: App {
    var body: some Scene {
        WindowGroup {
            MainView()
                .accentColor(Color("AccentColor"))
        }
    }
}
