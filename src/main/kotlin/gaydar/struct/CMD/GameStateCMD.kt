package gaydar.struct.CMD

import com.badlogic.gdx.math.Vector2
import gaydar.GameListener
import gaydar.register
import gaydar.struct.Actor
import gaydar.struct.Bunch
import gaydar.struct.NetGuidCacheObject
import gaydar.util.debugln

object GameStateCMD : GameListener
{
  init
  {
    register(this)
  }

  override fun onGameOver()
  {
    SafetyZonePosition.setZero()
    SafetyZoneRadius = 0f
    SafetyZoneBeginPosition.setZero()
    SafetyZoneBeginRadius = 0f
    PoisonGasWarningPosition.setZero()
    PoisonGasWarningRadius = 0f
    RedZonePosition.setZero()
    RedZoneRadius = 0f
    TotalWarningDuration = 0f
    ElapsedWarningDuration = 0f
    TotalReleaseDuration = 0f
    ElapsedReleaseDuration = 0f
    NumJoinPlayers = 0
    NumAlivePlayers = 0
    NumAliveTeams = 0
    RemainingTime = 0
    MatchElapsedMinutes = 0
    isTeamMatch = false
  }

  var isTeamMatch = false
  var TotalWarningDuration = 0f
  var ElapsedWarningDuration = 0f
  var RemainingTime = 0
  var MatchElapsedMinutes = 0
  val SafetyZonePosition = Vector2()
  var SafetyZoneRadius = 0f
  val SafetyZoneBeginPosition = Vector2()
  var SafetyZoneBeginRadius = 0f
  val PoisonGasWarningPosition = Vector2()
  var PoisonGasWarningRadius = 0f
  val RedZonePosition = Vector2()
  var RedZoneRadius = 0f
  var TotalReleaseDuration = 0f
  var ElapsedReleaseDuration = 0f
  var NumJoinPlayers = 0
  var NumAlivePlayers = 0
  var NumAliveTeams = 0

  fun process(actor : Actor, bunch : Bunch, repObj : NetGuidCacheObject?, waitingHandle : Int, data : HashMap<String, Any?>) : Boolean
  {
    try
    {
      with(bunch) {
        when (waitingHandle)
        {
          16   ->
          {
            val GameModeClass = propertyObject()
            val b = GameModeClass
          }
          17   ->
          {
            val SpectatorClass = propertyObject()
            val b = SpectatorClass
          }
          18   ->
          {
            val bReplicatedHasBegunPlay = propertyBool()
            val b = bReplicatedHasBegunPlay
          }
          19   ->
          {
            val ReplicatedWorldTimeSeconds = propertyFloat()
            val b = ReplicatedWorldTimeSeconds
          }
          20   ->
          {
            val MatchState = propertyName()
            val b = MatchState
          }
          21   ->
          {
            val ElapsedTime = propertyInt()
            val b = ElapsedTime
          }
          22   ->
          {
            val MatchId = propertyString()
            val b = MatchId
          }
          23   ->
          {
            val MatchShortGuid = propertyString()
            val b = MatchShortGuid
          }
          24   -> propertyBool()//bIsCustomGame
          25   -> propertyBool() //bIsWinnerZombieTeam
          26   ->
          {
            val NumTeams = propertyInt()
            val b = NumTeams
          }
          27   ->
          {
            RemainingTime = propertyInt()
          }
          28   ->
          {
            MatchElapsedMinutes = propertyInt()
          }
          29   ->
          {
            val bTimerPaused = propertyBool()
            val b = bTimerPaused
          }
//        30->{
//          print("")
//        }
//        31->{
//          print("")
//        }
          34   ->
          {
            NumJoinPlayers = propertyInt()
          }
          35   ->
          {
            NumAlivePlayers = propertyInt()
//          println(NumAlivePlayers)
          }
          36   ->
          {
            val NumAliveZombiePlayers = propertyInt()
            val b = NumAliveZombiePlayers
          }
          37   ->
          {
            NumAliveTeams = propertyInt()
          }
          38   ->
          {
            val NumStartPlayers = propertyInt()
            val b = NumStartPlayers
          }
          39   ->
          {
            val NumStartTeams = propertyInt()
            val b = NumStartTeams
          }
          40   ->
          {
            val pos = propertyVector()
            SafetyZonePosition.set(pos.x, pos.y)
          }
          41   ->
          {
            SafetyZoneRadius = propertyFloat()
          }
          42   ->
          {
            val pos = propertyVector()
            PoisonGasWarningPosition.set(pos.x, pos.y)
          }
          43   ->
          {
            PoisonGasWarningRadius = propertyFloat()
          }
          44   ->
          {
            val pos = propertyVector()
            RedZonePosition.set(pos.x, pos.y)

            val b = RedZonePosition
          }
          45   ->
          {
            RedZoneRadius = propertyFloat()
            val b = RedZoneRadius
          }
//        44->{
//
//        }
          47   ->
          {
            TotalReleaseDuration = propertyFloat()
            val b = TotalReleaseDuration
          }
          48   ->
          {
            ElapsedReleaseDuration = propertyFloat()
            val b = ElapsedReleaseDuration
          }
          49   ->
          {
            TotalWarningDuration = propertyFloat()
          }
          50   ->
          {
            ElapsedWarningDuration = propertyFloat()
          }
          51   ->
          {
            val bIsGasRelease = propertyBool()
          }
          52   ->
          {
            isTeamMatch = propertyBool()
          }
          53   ->
          {
            val bIsZombieMode = propertyBool()
          }
          54   ->
          {
            val pos = propertyVector()
            SafetyZoneBeginPosition.set(pos.x, pos.y)
          }
          55   ->
          {
            SafetyZoneBeginRadius = propertyFloat()
          }
          56   ->
          {
            val MatchStartType = propertyByte()
          }
          57   -> return false
          else -> return ActorCMD.process(actor, bunch, repObj, waitingHandle, data)
        }
        return true
      }
    }
    catch (e : Exception)
    {
      debugln { ("GameStateCMD is throwing somewhere: $e ${e.stackTrace} ${e.message} ${e.cause}") }
    }
    return false
  }
}