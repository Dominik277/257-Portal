package Portal

import Portal.a257.R
import Portal.dodajNovo.*
import Portal.fragmenti.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val dodajNovoVijesti = DodajNovoVijesti()
    val dodajNovuZabava = DodajNovoZabava()
    val dodajNoviOglas = DodajNovoOglasnik()
    val dodajNoviSport = DodajNovoSport()
    val dodajNovuObavijest = DodajNovoObavijesti()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val drawerToggle =
            ActionBarDrawerToggle(this, main_drawer_layout, R.string.open, R.string.close)
        main_drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        val naslovnicaFragment = NaslovnicaFragment()
        val vijestiFragment = VijestiFragment()
        val obavijestiFragment = ObavijestiFragment()
        val oglasnikFragment = OglasnikFragment()
        val sportFragment = SportFragment()
        val zabavaFragment = ZabavaFragment()
        val vrijemeFragment = VrijemeFragment()
        val infoFragment = InfoFragment()
        val priceCitateljaFragment = PriceCitateljaFragment()
        val kontaktFragment = KontaktFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout_host, naslovnicaFragment)
            commit()
        }

        navigation_drawer.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.vijestiNavDrawer -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout_host, vijestiFragment)
                        addToBackStack(null)
                        commit()
                        main_drawer_layout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.obavijestiNavDrawer -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout_host, obavijestiFragment)
                        addToBackStack(null)
                        commit()
                        main_drawer_layout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.oglasnikNavDrawer -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout_host, oglasnikFragment)
                        addToBackStack(null)
                        commit()
                        main_drawer_layout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.sportNavDrawer -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout_host, sportFragment)
                        addToBackStack(null)
                        commit()
                        main_drawer_layout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.zabavaNavDrawer -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout_host, zabavaFragment)
                        addToBackStack(null)
                        commit()
                        main_drawer_layout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.priceCitateljaNavDrawer -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout_host, priceCitateljaFragment)
                        addToBackStack(null)
                        commit()
                        main_drawer_layout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                else -> false
            }
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.vrijemeBottomNav -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout_host, vrijemeFragment)
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                R.id.naslovnicaBottomNav -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout_host, naslovnicaFragment)
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                R.id.infoBottomNav -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout_host, infoFragment)
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                R.id.kontaktBottomNav -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayout_host, kontaktFragment)
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                else -> false
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                main_drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            R.id.menu_dodajNovuVijest -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayout_host, dodajNovoVijesti)
                    addToBackStack(null)
                    commit()
                }
                true
            }
            R.id.menu_dodajNovuObavijest -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayout_host, dodajNovuObavijest)
                    addToBackStack(null)
                    commit()
                }
                true
            }
            R.id.menu_dodajNovuZabava -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayout_host, dodajNovuZabava)
                    addToBackStack(null)
                    commit()
                }
                true
            }
            R.id.menu_dodajNoviOglas -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayout_host, dodajNoviOglas)
                    addToBackStack(null)
                    commit()
                }
                true
            }
            R.id.menu_dodajNoviSport -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayout_host, dodajNoviSport)
                    addToBackStack(null)
                    commit()
                }
                true
            }
            else -> false
        }
    }
}

