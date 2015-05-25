/*
 * Copyright 2011-2013 Sisioh Project and others. (http://www.sisioh.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.sisioh.dddbase.core.lifecycle.async

import org.sisioh.dddbase.core.lifecycle.ResultWithEntity
import org.sisioh.dddbase.core.model.{ Entity, Identifier }
import scala.concurrent.Future

trait AsyncResultWithEntity[+R <: AsyncEntityWriter[ID, E], ID <: Identifier[_], E <: Entity[ID]]
  extends ResultWithEntity[R, ID, E, Future]

object AsyncResultWithEntity {

  def apply[R <: AsyncEntityWriter[ID, T], ID <: Identifier[_], T <: Entity[ID]](result: R, entity: T): AsyncResultWithEntity[R, ID, T] = AsyncResultWithEntityImpl(result, entity)

  def unapply[R <: AsyncEntityWriter[ID, T], ID <: Identifier[_], T <: Entity[ID]](resultWithEntity: AsyncResultWithEntity[R, ID, T]): Option[(R, T)] =
    Some(resultWithEntity.result, resultWithEntity.entity)

}

private[async] case class AsyncResultWithEntityImpl[+R <: AsyncEntityWriter[ID, T], ID <: Identifier[_], T <: Entity[ID]](result: R, entity: T) extends AsyncResultWithEntity[R, ID, T]
