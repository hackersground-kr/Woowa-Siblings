//
//  Coordinate.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import Foundation

struct Coordinate: Codable {
    let meta: CoordinateMeta
    let documents: [CoordinateDocuments]
}

struct CoordinateMeta: Codable {
    let totalCount: Int
}

struct CoordinateDocuments: Codable {
    let x: CGFloat
    let y: CGFloat
}
