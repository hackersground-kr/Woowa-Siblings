//
//  MapVC.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import UIKit
import NMapsMap

class MapVC: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()

        let mapView = NMFMapView(frame: view.frame)
        view.addSubview(mapView)
    }
}
