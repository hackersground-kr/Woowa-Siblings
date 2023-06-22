//
//  MapView.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import SwiftUI
import MapKit

struct MapView: View {
    @Binding var coordinates: [Coordinate]
    
  
    
    @State private var region = MKCoordinateRegion(center: CLLocationCoordinate2D(latitude: 37.5666791, longitude: 126.9782914), span: MKCoordinateSpan(latitudeDelta: 0.5, longitudeDelta: 0.5))
    var body: some View {
        Map(coordinateRegion: $region, showsUserLocation: false, userTrackingMode: .constant(.follow))
    }
}
