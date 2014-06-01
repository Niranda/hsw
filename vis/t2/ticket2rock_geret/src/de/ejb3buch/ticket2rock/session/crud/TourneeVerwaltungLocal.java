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

import de.ejb3buch.ticket2rock.entity.Tournee;

@Local
public interface TourneeVerwaltungLocal {

    /**
     * Legt eine Tournee-Entit�t in der Persistenzschicht an
     * @param tournee pojo mit den Attributen der Tournee
     */
	public void createTournee(Tournee tournee);
	
	/**
	 * Selektiert eine Tournee-Entit�t mit einen gegebenen Namen
	 * @param name Name der Tournee, die selektiert werden soll
	 * @return Tournee Objekt, das den bestimmten Namen besitzt.
	 * Existiert keine Tournee mit diesem Namen, ist der R�ckgabewert null
	 */
	public Tournee getTourneeByName(String name);

	/**
	 * Aktualisiert eine Tournee-Entit�t in der Persistenzschicht
	 * @param tournee Tournee Objekt, das persistiert werden soll
	 */
	public void updateTournee(Tournee tournee);

	/**
	 * L�scht eine Tournee-Entit�t aus der Persistenzschicht
	 * @param tourneeId id der zu l�schenden Tournee
	 */
	public void deleteTournee(Integer tourneeId);

	/**
	 * selektiert alle Tournee-Entit�ten
	 * @return Tournee-Entit�ten
	 */
	public Collection<Tournee> getTourneen();
	
	/**
	 * Selektiert eine Tournee f�r eine gegebene Id
	 * @param tourneeId
	 * @return Tournee Entit�t, null fallse keine Tournee-Entit�t
	 * mit dieser id existiert
	 */
	public Tournee getTourneeById(Integer tourneeId);
}
