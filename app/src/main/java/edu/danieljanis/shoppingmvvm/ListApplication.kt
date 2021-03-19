package edu.danieljanis.shoppingmvvm

import android.app.Application
import edu.danieljanis.shoppingmvvm.database.ListDatabase
import edu.danieljanis.shoppingmvvm.database.ListRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/* Daniel Janis */
/* Project 3 - 4011 Android Apps */

class ListApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ListApplication))

        // instance() automatically recognizes that the app context fits here
        bind() from singleton { ListDatabase(instance()) }
        bind() from singleton { ListRepository(instance()) }
        // use provider here so we can use the instance from the TitlesActivity
        bind() from provider {
            // Since the repo was instantiated on line 21, can use that repo here via instance()
            ListViewModelFactory(instance())
        }
    }
}