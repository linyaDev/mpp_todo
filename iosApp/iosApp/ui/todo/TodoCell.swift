//
//  TodoCell.swift
//  iosApp
//
//  Created by Ilya Nikolaev on 01/08/2019.
//

import UIKit

class TodoCell: UITableViewCell {

    @IBOutlet var title: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    
    
}
