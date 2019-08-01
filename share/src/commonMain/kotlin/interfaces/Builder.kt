package com.linya.utils.interfaces

import com.linya.utils.ribs.Router


abstract class Builder{
    abstract fun build(dependencies: RouterDependencies): Router
}
