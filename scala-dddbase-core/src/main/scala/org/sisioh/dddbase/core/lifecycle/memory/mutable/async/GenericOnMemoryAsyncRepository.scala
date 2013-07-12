/*
 * Copyright 2010 TRICREO, Inc. (http://tricreo.jp/)
 * Copyright 2011 Sisioh Project and others. (http://www.sisioh.org/)
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
package org.sisioh.dddbase.core.lifecycle.memory.mutable.async

import org.sisioh.dddbase.core.lifecycle.memory.mutable.sync.GenericOnMemorySyncRepository
import org.sisioh.dddbase.core.model.{Identity, EntityCloneable, Entity}
import scala.concurrent.ExecutionContext

/**
 * 汎用的な非同期型オンメモリ可変リポジトリ。
 *
 * @param core 内部で利用するオンメモリ可変リポジトリ。
 * @tparam ID 識別子の型
 * @tparam T エンティティの型
 */
class GenericOnMemoryAsyncRepository[ID <: Identity[_], T <: Entity[ID] with EntityCloneable[ID, T] with Ordered[T]]
(protected val core: GenericOnMemorySyncRepository[ID, T] = GenericOnMemorySyncRepository[ID, T]())
(implicit val executor: ExecutionContext)
  extends OnMemoryAsyncRepository[GenericOnMemoryAsyncRepository[ID, T], GenericOnMemorySyncRepository[ID, T], ID, T] {
}

/**
 * コンパニオンオブジェクト。
 */
object GenericOnMemoryAsyncRepository {

  /**
   * ファクトリメソッド。
   *
   * @param core 内部で利用するオンメモリ可変リポジトリ。
   * @tparam ID 識別子の型
   * @tparam T エンティティの型
   * @return [[org.sisioh.dddbase.core.lifecycle.memory.mutable.async.GenericOnMemoryAsyncRepository]]
   */
  def apply[ID <: Identity[_], T <: Entity[ID] with EntityCloneable[ID, T] with Ordered[T]]
  (core: GenericOnMemorySyncRepository[ID, T] = GenericOnMemorySyncRepository[ID, T]())
  (implicit executor: ExecutionContext) =
    new GenericOnMemoryAsyncRepository(core)

  /**
   * エクストラクタメソッド。
   *
   * @param repository [[org.sisioh.dddbase.core.lifecycle.memory.mutable.async.GenericOnMemoryAsyncRepository]]
   * @tparam ID 識別子の型
   * @tparam T エンティティの型
   * @return 構成要素
   */
  def unapply[ID <: Identity[_], T <: Entity[ID] with EntityCloneable[ID, T] with Ordered[T]]
  (repository: GenericOnMemoryAsyncRepository[ID, T]): Option[GenericOnMemorySyncRepository[ID, T]] =
    Some(repository.core)

}

