//
//  MapView.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import SwiftUI
import NMapsMap

struct MapView: UIViewRepresentable {
    
    let coordinates: [Coordinate]
    
    func makeUIView(context: Context) -> UIView {
        let mapView = NMFMapView(frame: .infinite)
        return mapView
    }

    func updateUIView(_ uiViewController: UIView, context: Context) {
    }
}
