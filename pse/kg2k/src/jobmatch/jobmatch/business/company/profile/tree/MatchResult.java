// $Id: MatchResult.java,v 1.1 2000/06/13 11:04:46 locher Exp $

package jobmatch.business.company.profile.tree;

/**
 *  Represents the result of the matching process.
 *
 *  @since June 13 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public interface MatchResult {
    
    public double getScore();
    public boolean hasMandatory();
       
} //interface

// Document history
/*
 * $Log: MatchResult.java,v $
 * Revision 1.1  2000/06/13 11:04:46  locher
 * new match interface
 *
 */

