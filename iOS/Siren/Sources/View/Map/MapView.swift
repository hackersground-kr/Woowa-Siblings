//
//  MapView.swift
//  Siren
//
//  Created by Mercen on 2023/06/21.
//

import SwiftUI

struct MapView: UIViewControllerRepresentable {
    
    @Binding var active: Bool
    let startDest: String
    let startX, startY: CGFloat
    let endDest: String
    let endX, endY: CGFloat
    
    func activate(_ value: Bool) {
        self.active = value
    }
    
    func makeUIViewController(context: Context) -> MapVC {
        let vc = MapVC()
        vc.startNavigate(withStartDest: startDest,
                         startX: startX,
                         startY: startY,
                         endDest: endDest,
                         endX: endX,
                         endY: endY,
                         activate: activate)
        return vc
    }

    func updateUIViewController(_ uiViewController: MapVC, context: Context) {
    }
}
