/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets f�r
 *  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Jo Ehm, Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen J�rg, Holger Koschek,
 *  Carsten Sahling, Roman Schloemmer
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package de.ejb3buch.ticket2rock.session.crud;

import java.util.Collection;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Band;

@Local
public interface BandVerwaltung {

    /**
     * Legt eine Band-Entit�t in der Persistenzschicht an
     * @param band band pojo mit den Attributen der Band
     */
	public void createBand(Band band);
	
	/**
	 * Selektiert eine Band-Entit�t mit einen gegebenen Namen
	 * @param name Name der Band, die selektiert werden soll
	 * @return Band Objekt, das den bestimmten Namen besitzt.
	 * Existiert keine Band mit diesem Namen, ist der R�ckgabewert null
	 */
	public Band getBandByName(String name);

	/**
	 * Aktualisiert eine Band-Entit�t in der Persistenzschicht
	 * @param band Band Objekt, das persistiert werden soll
	 */
	public void updateBand(Band band);

	/**
	 * L�scht eine Band-Entit�t aus der Persistenzschicht
	 * @param bandId id der zu l�schenden Band
	 */
	public void deleteBand(Integer bandId);


	
	/**
	 * selektiert alle Band-Entit�ten
	 * @return Band-Entit�ten
	 */
	public Collection<Band> getBands();
	
	/**
	 * Selektiert eine Band f�r eine gegebene Id
	 * @param bandId
	 * @return Band Entit�t, null fallse keine Band-Entit�t
	 * mit dieser id existiert
	 */
	public Band getBandById(Integer bandId);
    

}
