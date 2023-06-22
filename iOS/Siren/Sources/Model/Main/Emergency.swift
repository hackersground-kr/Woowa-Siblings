//
//  Emergency.swift
//  Siren
//
//  Created by Mercen on 2023/06/22.
//

import Foundation

struct Emergency: Codable {
    let hospitalName, telephone,
        emergencyRoom, operatingRoom,
        geIntensiveCareUnit,
        thIntensiveCareUnit, intensiveCareUnit,
        neurologicalIntensiveCareUnit,
        neonatalIntensiveCareUnit,
        neurosurgeryIntensiveCareUnit,
        inpatientRoom, surgicalInpatientRoom,
        neurologyInpatientRoom: String
}
