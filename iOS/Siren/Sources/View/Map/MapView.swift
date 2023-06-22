//
//  MapView.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import SwiftUI

struct MapView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> MapVC {
        let vc = MapVC()
        return vc
    }

    func updateUIViewController(_ uiViewController: MapVC, context: Context) {
    }
}
