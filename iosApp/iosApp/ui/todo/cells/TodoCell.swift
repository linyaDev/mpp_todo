//
//  TodoCell.swift
//  iosApp
//
//  Created by Ilya Nikolaev on 01/08/2019.
//

import UIKit
import BEMCheckBox

class TodoCell: UITableViewCell {

    @IBOutlet var title: UILabel!
    @IBOutlet var desc: UILabel!
    let checkbox = BEMCheckBox(frame: CGRect(x: 25, y: 15, width: 20, height: 20))
    let checkboxListener = CheckBoxListener()
    
    class CheckBoxListener: NSObject,BEMCheckBoxDelegate{
        var selector:(()->Void)? = nil
        
        /*
        func didTap(_ checkBox: BEMCheckBox) {
            selector?()
        }*/
        func animationDidStop(for checkBox: BEMCheckBox) {
            selector?()
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        checkbox.boxType = BEMBoxType.circle
        addSubview(checkbox)
        checkbox.delegate = checkboxListener
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        //super.setSelected(selected, animated: animated)
        // Configure the view for the selected state
    }
    
    
    
}
