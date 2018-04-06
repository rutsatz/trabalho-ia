/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import NineMensMorris.GameInfo;
import NineMensMorris.PlayerAgent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author Carlos
 */
public class NotSoDummyAgent implements PlayerAgent
{

    @Override
    public String setPiece(GameInfo info) 
    {
        List<String> spots = info.getEmptySpots();
        
                                
        for ( String target : spots )
        {
            if ( info.canPlayerFormLineOfThree(target) )
            {
                return target;
            }
        }
        
        for ( String target : spots )
        {
            if ( info.canOpponentFormLineOfThree(target) )
            {
                return target;
            }
        }
                                
        Random random = new Random();
        
        int selected = random.nextInt(spots.size());
        
        return spots.get(selected);
    }
    
    public String removePiece(GameInfo info) 
    {
        String remove = "";
                
        List<String> opponentAllowedMoves = info.getOpponentAllowedMoves();
        
        for ( String opAllowedMove : opponentAllowedMoves )
        {            
            String[] coords = opAllowedMove.split(";");
            
            String source = ""; 
            
            if ( coords.length > 1 )
            {
                for ( int x=0; x<coords.length;x++)
                {
                    if ( x == 0 )
                    {
                        source = coords[x];
                    }
                    else if ( info.canOpponentFormLineOfThree( coords[x]) && 
                             !info.isOpponentLineOfThree( source ) )
                    {
                        return source;
                    }
                }
                
            }
        }
        
        List<String> allowedRemoves = info.getAllowedRemoves();
                        
        if ( !allowedRemoves.isEmpty())
        {
            Random random = new Random();
        
            int selected = random.nextInt( allowedRemoves.size());
            
            remove = allowedRemoves.get( selected );
                        
        }
        
        return remove;   
    }

    @Override
    public String movePiece(GameInfo info) 
    {
        
        String move = "";
        
        List<String> allowedMoves = info.getAllowedMoves();
        List<String> opAllowedMoves = info.getOpponentAllowedMoves();
        
        for ( String allowedMove : allowedMoves )
        {            
            String[] coords = allowedMove.split(";");
            
            if ( coords.length > 1 )
            {
                String source = coords[0];
                
                for ( int x=0; x<coords.length;x++)
                {
                    if ( x > 0 && info.canPlayerFormLineOfThree( coords[x]) )
                    {
                        if (!info.isPlayerLineOfTwo(source))
                        {
                            return allowedMove;
                        }
                    }
                }                
            }
        }
        
        List<String> opLineOfThreeTargets = new ArrayList(); 
        
        for ( String opAllowedMove : opAllowedMoves  )
        {
            
            String[] coords = opAllowedMove.split(";");
            
            if ( coords.length > 1 )
            {                
                for ( int x=0; x<coords.length;x++)
                {
                    if ( x > 0 && info.canOpponentFormLineOfThree( coords[x]) )
                    {
                        opLineOfThreeTargets.add( coords[x] );
                    }
                }                
            }
        }
        
        for ( String allowedMove : allowedMoves )
        {            
            String[] coords = allowedMove.split(";");
            
            if ( coords.length > 1 )
            {                
                for ( int x=0; x<coords.length;x++)
                {
                    if ( x > 0 && opLineOfThreeTargets.contains( coords[x]) )
                    {                        
                        return allowedMove;                        
                    }
                }                
            }
        }
        
        if ( !allowedMoves.isEmpty())
        {
            Random random = new Random();
        
            int selected = random.nextInt(allowedMoves.size());
            
            move = allowedMoves.get( selected );
                        
        }
        
        return move;
    }
    
    
    
    

    
}
