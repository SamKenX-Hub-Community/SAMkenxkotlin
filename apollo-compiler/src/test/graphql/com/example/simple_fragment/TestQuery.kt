// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.simple_fragment

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.simple_fragment.fragment.HeroDetails
import com.example.simple_fragment.fragment.HumanDetails
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "LocalVariableName", "RemoveExplicitTypeArguments",
    "NestedLambdaShadowedImplicitParameter")
class TestQuery : Query<TestQuery.Data, TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID
  override fun queryDocument(): String = QUERY_DOCUMENT
  override fun wrapData(data: Data?): Data? = data
  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES
  override fun name(): OperationName = OPERATION_NAME
  override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper {
    Data(it)
  }

  data class Hero(
    val __typename: String,
    val fragments: Fragments
  ) {
    fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
      it.writeString(RESPONSE_FIELDS[0], __typename)
      fragments.marshaller().marshal(it)
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forString("__typename", "__typename", null, false, null),
          ResponseField.forString("__typename", "__typename", null, false, null)
          )

      operator fun invoke(reader: ResponseReader): Hero {
        val __typename = reader.readString(RESPONSE_FIELDS[0])
        val fragments = reader.readConditional(RESPONSE_FIELDS[1]) { conditionalType, reader ->
          val heroDetails = HeroDetails(reader)
          val humanDetails = if (HumanDetails.POSSIBLE_TYPES.contains(conditionalType))
              HumanDetails(reader) else null
          Fragments(
            heroDetails = heroDetails,
            humanDetails = humanDetails
          )
        }

        return Hero(
          __typename = __typename,
          fragments = fragments
        )
      }
    }

    data class Fragments(
      val heroDetails: HeroDetails,
      val humanDetails: HumanDetails?
    ) {
      fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
        heroDetails.marshaller().marshal(it)
        humanDetails?.marshaller()?.marshal(it)
      }
    }
  }

  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
      it.writeObject(RESPONSE_FIELDS[0], hero?.marshaller())
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField.forObject("hero", "hero", null, true, null)
          )

      operator fun invoke(reader: ResponseReader): Data {
        val hero = reader.readObject<Hero>(RESPONSE_FIELDS[0]) { reader ->
          Hero(reader)
        }

        return Data(
          hero = hero
        )
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "f258982f7e2b12d61c678aa68c0baee6ae552768b670dd2f56b92d36c1cfd83b"

    val QUERY_DOCUMENT: String = """
        |query TestQuery {
        |  hero {
        |    __typename
        |    ...HeroDetails
        |    ...HumanDetails
        |  }
        |}
        |fragment HeroDetails on Character {
        |  __typename
        |  name
        |}
        |fragment HumanDetails on Human {
        |  __typename
        |  name
        |}
        """.trimMargin()

    val OPERATION_NAME: OperationName = OperationName { "TestQuery" }
  }
}
