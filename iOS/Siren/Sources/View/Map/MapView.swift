//
//  MapView.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import SwiftUI
import MapKit

struct MapLocation: Identifiable {
    let id = UUID()
    let name: String
    let latitude: Double
    let longitude: Double
}

struct MapView: View {
    @Binding var coordinates: [Coordinate]

    @State private var region = MKCoordinateRegion(center: CLLocationCoordinate2D(latitude: 0, longitude: 0),
                                                   span: MKCoordinateSpan(latitudeDelta: 0.5, longitudeDelta: 0.5))
    var body: some View {
        Map(coordinateRegion: $region,
            showsUserLocation: false,
            userTrackingMode: .constant(.follow),
            annotationContent: { location in
            MapPin(coordinate: location.coordinate, tint: .red)
        }) {
            Image("Pin")
        }
    }
}
