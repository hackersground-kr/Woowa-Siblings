//
//  MapView.swift
//  Siren
//
//  Created by Mercen on 2023/06/21.
//

import SwiftUI

struct MapView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> MapVC {
        return MapVC()
    }

    func updateUIViewController(_ uiViewController: MapVC, context: Context) {
        // Update the view controller if needed
    }
}
