package org.sisioh.dddbase.core.lifecycle.memory.sync

import org.sisioh.dddbase.core.lifecycle.sync._
import org.sisioh.dddbase.core.model._
import scala.Some
import scala.Some
import scala.Some
import scala.Some
import scala.util._

/**
  * [[org.sisioh.dddbase.core.lifecycle.memory.sync.OnMemorySyncRepositorySupport]]にOption型のサポートを追加するトレイト。
  *
  * @tparam R 当該リポジトリを実装する派生型
  * @tparam ID エンティティの識別子の型
  * @tparam T エンティティの型
  */
trait OnMemorySyncRepositorySupportByOption
[+R <: SyncRepository[_, ID, T],
 ID <: Identity[_],
 T <: Entity[ID] with EntityCloneable[ID, T] with Ordered[T]]
  extends OnMemorySyncRepositorySupport[R, ID, T]
     with SyncEntityReaderByOption[ID, T] {

     override def resolveOption(identity: ID) = synchronized {
       contains(identity).flatMap {
         _ =>
           Try {
             Some(entities(identity).clone)
           }.recoverWith {
             case ex: NoSuchElementException =>
               Success(None)
           }
       }
     }

   }
