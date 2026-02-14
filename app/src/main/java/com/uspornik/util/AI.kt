package com.uspornik.util

import com.uspornik.domain.model.Category
import com.uspornik.domain.model.Transaction
import com.uspornik.domain.model.TransactionType
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.abs
import kotlin.math.pow
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.ios.IosEmojiProvider

object AI {
    const val RECENT_WEIGHT = 0.6
    const val OVERALL_WEIGHT = 0.4
    const val RECENT_DAYS = 7
    const val HIGH_CATEGORY = 30.0
    const val MEDIUM_CATEGORY = 20.0
    const val BUDGET_WARNING = 90.0
    const val BUDGET_ALERT = 70.0
    const val MIN_CONFIDENCE = 15
    const val MAX_CONFIDENCE = 100.0
    const val ANOMALY_THRESHOLD = 2.0
    const val INCREASING = "increasing"
    const val DECREASING = "decreasing"
    const val STABLE = "stable"

    object Emoji {
        const val FOOD = "🍔"
        const val TRANSPORT = "🚗"
        const val HOUSING = "🏠"
        const val ENTERTAINMENT = "🎬"
        const val CLOTHING = "👕"
        const val HEALTH = "💊"
        const val EDUCATION = "📚"
        const val SHOPPING = "🛍️"
        const val TRAVEL = "✈️"
        const val UTILITIES = "💡"
        const val INSURANCE = "🛡️"
        const val GIFTS = "🎁"
        const val SALARY = "💰"
        const val BONUS = "🎯"
        const val INVESTMENT = "📈"
        const val SAVINGS = "🏦"
        const val OTHER = "📦"
        const val COFFEE = "☕"
        const val RESTAURANT = "🍽️"
        const val GROCERIES = "🛒"
        const val GAS = "⛽"
        const val PARKING = "🅿️"
        const val TAXI = "🚕"
        const val BUS = "🚌"
        const val TRAIN = "🚆"
        const val RENT = "🔑"
        const val BILLS = "📄"
        const val INTERNET = "🌐"
        const val PHONE = "📱"
        const val NETFLIX = "📺"
        const val SPOTIFY = "🎵"
        const val GYM = "🏋️"
        const val SPORTS = "⚽"
        const val CINEMA = "🎭"
        const val BOOKS = "📖"
        const val PHARMACY = "💉"
        const val DOCTOR = "👨‍⚕️"
        const val BEAUTY = "💄"
        const val HAIRCUT = "💇"
        const val SHOES = "👟"
        const val ACCESSORIES = "👜"
        const val ELECTRONICS = "⚡"
        const val FURNITURE = "🛋️"
        const val PETS = "🐾"
        const val KIDS = "👶"
        const val PARTY = "🎉"
        const val WEDDING = "💒"
        const val VACATION = "🏖️"
        const val HOTEL = "🏨"
        const val FLIGHT = "🛫"
        const val CHARITY = "❤️"
        const val TAXES = "💼"
        const val LOAN = "💳"
        const val CASH = "💵"
        const val WALLET = "👛"
        const val PIGGYBANK = "🐷"
        const val CHART_UP = "📊"
        const val CHART_DOWN = "📉"
        const val WARNING = "⚠️"
        const val CHECK = "✅"
        const val FIRE = "🔥"
        const val STAR = "⭐"
        const val ROCKET = "🚀"
        const val TROPHY = "🏆"
        const val TARGET = "🎯"
        const val CALENDAR = "📅"
        const val CLOCK = "⏰"
        const val BELL = "🔔"
        const val LOCK = "🔒"
        const val KEY = "🔑"
        const val IDEA = "💡"
        const val QUESTION = "❓"
        const val EXCLAMATION = "❗"
        const val THUMBS_UP = "👍"
        const val THUMBS_DOWN = "👎"
        const val CLAP = "👏"
        const val MUSCLE = "💪"
        const val BRAIN = "🧠"
        const val EYE = "👁️"
        const val MONEY_WINGS = "💸"
        const val MONEY_BAG = "💰"
        const val CREDIT_CARD = "💳"
        const val RECEIPT = "🧾"
        const val CALCULATOR = "🧮"
        const val BRIEFCASE = "💼"
        const val PACKAGE = "📦"
        const val INBOX = "📥"
        const val OUTBOX = "📤"
        const val PIN = "📌"
        const val PAPERCLIP = "📎"
        const val MEMO = "📝"
        const val FOLDER = "📁"
        const val TRASH = "🗑️"
        const val RECYCLE = "♻️"
        const val SETTINGS = "⚙️"
        const val SEARCH = "🔍"
        const val FILTER = "🔽"
        const val PLUS = "➕"
        const val MINUS = "➖"
        const val MULTIPLY = "✖️"
        const val DIVIDE = "➗"
        const val EQUAL = "🟰"
        const val PERCENT = "💯"
        const val ARROW_UP = "⬆️"
        const val ARROW_DOWN = "⬇️"
        const val ARROW_RIGHT = "➡️"
        const val ARROW_LEFT = "⬅️"
    }

    object Colors {
        const val RED = "#FF6B6B"
        const val BLUE = "#4ECDC4"
        const val GREEN = "#2ECC71"
        const val YELLOW = "#F7DC6F"
        const val PURPLE = "#9B59B6"
        const val ORANGE = "#FFA07A"
        const val PINK = "#FF69B4"
        const val CYAN = "#00CED1"
        const val LIME = "#98D8C8"
        const val INDIGO = "#6C63FF"
        const val TEAL = "#45B7D1"
        const val AMBER = "#F39C12"
        const val GRAY = "#B0B0B0"
        const val DARK_GRAY = "#555555"
        const val LIGHT_GRAY = "#CCCCCC"
        const val BLACK = "#000000"
        const val WHITE = "#FFFFFF"
    }

    object Grok {
        const val API_URL = "https://api.x.ai/v1/chat/completions"
        const val grok = GrokChat(apiKey = BuildConfig.GROK_API_KEY)
        const val MODEL = "grok-beta"
        const val MAX_TOKENS = 1000
        const val TEMPERATURE = 0.7
        const val TIMEOUT = 30000L

        const val ROLE_SYSTEM = "system"
        const val ROLE_USER = "user"
        const val ROLE_ASSISTANT = "assistant"

        const val PROMPT_ANALYZE = "Analyzuj tyto finanční data a dej mi praktické rady:"
        const val PROMPT_SAVINGS = "Jak můžu ušetřit na základě těchto výdajů?"
        const val PROMPT_BUDGET = "Pomoz mi nastavit rozumný rozpočet:"
        const val PROMPT_CATEGORY = "Analyzuj výdaje v této kategorii:"
        const val PROMPT_PREDICT = "Odhadni moje výdaje do konce měsíce:"
        const val PROMPT_COMPARE = "Porovnej moje výdaje s průměrem:"

        const val CONTEXT_PREFIX = "Kontext: Uživatel má rozpočet"
        const val CONTEXT_SPENT = "Už utratil"
        const val CONTEXT_REMAINING = "Zbývá mu"
        const val CONTEXT_DAYS = "dní do výplaty"

        const val ERROR_NO_API_KEY = "Chybí API klíč pro Grok"
        const val ERROR_NETWORK = "Problém s připojením ke Grok AI"
        const val ERROR_TIMEOUT = "Grok AI neodpověděl včas"
        const val ERROR_INVALID = "Neplatná odpověď od Grok AI"
        const val ERROR_RATE_LIMIT = "Překročen limit požadavků"

        const val HEADER_AUTH = "Authorization"
        const val HEADER_CONTENT = "Content-Type"
        const val CONTENT_JSON = "application/json"
        const val AUTH_PREFIX = "Bearer"
    }
}

class AIInsight {

    fun analyze(transactions: List<Transaction>, budget: Double): Spending {
        val expenses = transactions.filter { it.type == TransactionType.EXPENSE }
        val totalSpent = expenses.sumOf { it.amount }
        val averageDaily = calculateDaily(expenses)

        val breakdown = expenses
            .groupBy { it.category }
            .mapValues { (_, txs) ->
                val total = txs.sumOf { it.amount }
                CategoryData(
                    category = txs.first().category,
                    amount = total,
                    percentage = if (totalSpent > 0) (total / totalSpent * 100) else 0.0,
                    count = txs.size,
                    average = total / txs.size,
                    trend = calculateTrend(txs)
                )
            }

        val recommendations = generateRecommend(
            breakdown.values.toList(),
            totalSpent,
            budget,
            averageDaily
        )

        val anomalies = detectAnomalies(expenses)
        val insights = generateInsights(expenses, breakdown.values.toList())

        return Spending(
            total = totalSpent,
            daily = averageDaily,
            categories = breakdown.values.toList().sortedByDescending { it.amount },
            recommendations = recommendations,
            anomalies = anomalies,
            insights = insights,
            trend = calculateOverall(expenses)
        )
    }

    fun predict(transactions: List<Transaction>): Prediction {
        val expenses = transactions.filter { it.type == TransactionType.EXPENSE }

        if (expenses.isEmpty()) {
            return Prediction(
                predicted = 0.0,
                confidence = 0.0,
                analyzed = 0,
                current = 0.0,
                daily = 0.0,
                trend = AI.STABLE,
                min = 0.0,
                max = 0.0
            )
        }

        val now = LocalDateTime.now()
        val first = expenses.minByOrNull { it.dateTime }?.dateTime ?: now
        val elapsed = ChronoUnit.DAYS.between(first, now).toInt() + 1
        val monthDays = now.toLocalDate().lengthOfMonth()

        val spent = expenses.sumOf { it.amount }
        val dailyAvg = spent / elapsed

        val recent = expenses.filter {
            ChronoUnit.DAYS.between(it.dateTime, now) <= AI.RECENT_DAYS
        }
        val recentAvg = if (recent.isNotEmpty()) {
            recent.sumOf { it.amount } / AI.RECENT_DAYS.toDouble()
        } else {
            dailyAvg
        }

        val weighted = (dailyAvg * AI.OVERALL_WEIGHT) + (recentAvg * AI.RECENT_WEIGHT)
        val remaining = monthDays - elapsed
        val predicted = spent + (weighted * remaining)

        val confidence = minOf(elapsed.toDouble() / AI.MIN_CONFIDENCE, 1.0) * AI.MAX_CONFIDENCE

        val variance = calculateVariance(expenses.map { it.amount })
        val deviation = kotlin.math.sqrt(variance)

        val min = spent + ((weighted - deviation) * remaining)
        val max = spent + ((weighted + deviation) * remaining)

        return Prediction(
            predicted = predicted.coerceAtLeast(spent),
            confidence = confidence,
            analyzed = elapsed,
            current = spent,
            daily = weighted,
            trend = calculateOverall(expenses),
            min = min.coerceAtLeast(spent),
            max = max
        )
    }

    fun getCategoryInsight(transactions: List<Transaction>, category: Category): CategoryInsight {
        val expenses = transactions.filter {
            it.category.id == category.id && it.type == TransactionType.EXPENSE
        }

        if (expenses.isEmpty()) {
            return CategoryInsight(
                category = category,
                total = 0.0,
                average = 0.0,
                frequency = 0.0,
                trend = AI.STABLE,
                tip = "Zatím žádné výdaje"
            )
        }

        val total = expenses.sumOf { it.amount }
        val average = total / expenses.size

        val now = LocalDateTime.now()
        val first = expenses.minByOrNull { it.dateTime }?.dateTime ?: now
        val days = ChronoUnit.DAYS.between(first, now).toInt() + 1
        val frequency = expenses.size.toDouble() / days

        val trend = calculateTrend(expenses)
        val tip = generateTip(expenses, average, trend)

        return CategoryInsight(
            category = category,
            total = total,
            average = average,
            frequency = frequency,
            trend = trend,
            tip = tip
        )
    }

    private fun calculateDaily(expenses: List<Transaction>): Double {
        if (expenses.isEmpty()) return 0.0

        val now = LocalDateTime.now()
        val first = expenses.minByOrNull { it.dateTime }?.dateTime ?: now
        val elapsed = ChronoUnit.DAYS.between(first, now).toInt() + 1

        return expenses.sumOf { it.amount } / elapsed
    }

    private fun calculateTrend(transactions: List<Transaction>): String {
        if (transactions.size < 3) return AI.STABLE

        val sorted = transactions.sortedBy { it.dateTime }
        val mid = sorted.size / 2

        val first = sorted.take(mid)
        val second = sorted.drop(mid)

        val firstAvg = first.sumOf { it.amount } / first.size
        val secondAvg = second.sumOf { it.amount } / second.size

        val change = ((secondAvg - firstAvg) / firstAvg) * 100

        return when {
            change > 10 -> AI.INCREASING
            change < -10 -> AI.DECREASING
            else -> AI.STABLE
        }
    }

    private fun calculateOverall(expenses: List<Transaction>): String {
        if (expenses.size < 5) return AI.STABLE

        val sorted = expenses.sortedBy { it.dateTime }
        val weekly = mutableListOf<Double>()
        var weekStart = sorted.first().dateTime
        var weekTotal = 0.0

        sorted.forEach { tx ->
            val diff = ChronoUnit.DAYS.between(weekStart, tx.dateTime)
            if (diff >= 7) {
                weekly.add(weekTotal)
                weekTotal = tx.amount
                weekStart = tx.dateTime
            } else {
                weekTotal += tx.amount
            }
        }
        weekly.add(weekTotal)

        if (weekly.size < 2) return AI.STABLE

        val firstAvg = weekly.take(weekly.size / 2).average()
        val lastAvg = weekly.drop(weekly.size / 2).average()

        val change = ((lastAvg - firstAvg) / firstAvg) * 100

        return when {
            change > 15 -> AI.INCREASING
            change < -15 -> AI.DECREASING
            else -> AI.STABLE
        }
    }

    private fun generateRecommend(
        categories: List<CategoryData>,
        total: Double,
        budget: Double,
        daily: Double
    ): List<Recommend> {
        val list = mutableListOf<Recommend>()

        val usage = if (budget > 0) (total / budget * 100) else 0.0

        when {
            usage > AI.BUDGET_WARNING -> {
                list.add(
                    Recommend(
                        type = RecommendType.WARNING,
                        title = "Kritický stav",
                        message = "Utratil jsi ${usage.toInt()}%",
                        priority = 1
                    )
                )
            }
            usage > AI.BUDGET_ALERT -> {
                list.add(
                    Recommend(
                        type = RecommendType.INFO,
                        title = "Sleduj rozpočet",
                        message = "Využito ${usage.toInt()}%",
                        priority = 2
                    )
                )
            }
        }

        categories
            .filter { it.percentage > AI.HIGH_CATEGORY }
            .forEach { cat ->
                list.add(
                    Recommend(
                        type = RecommendType.SUGGESTION,
                        title = "Vysoké: ${cat.category.name}",
                        message = "${cat.percentage.toInt()}% výdajů",
                        priority = 3
                    )
                )
            }

        categories
            .filter { it.trend == AI.INCREASING }
            .forEach { cat ->
                list.add(
                    Recommend(
                        type = RecommendType.TIP,
                        title = "Rostoucí: ${cat.category.name}",
                        message = "Výdaje rostou",
                        priority = 4
                    )
                )
            }

        if (budget > 0 && daily > budget / 30) {
            list.add(
                Recommend(
                    type = RecommendType.TIP,
                    title = "Vysoký průměr",
                    message = "${daily.toInt()} Kč/den",
                    priority = 5
                )
            )
        }

        return list.sortedBy { it.priority }
    }

    private fun detectAnomalies(expenses: List<Transaction>): List<Anomaly> {
        if (expenses.size < 5) return emptyList()

        val amounts = expenses.map { it.amount }
        val avg = amounts.average()
        val variance = calculateVariance(amounts)
        val deviation = kotlin.math.sqrt(variance)

        return expenses.mapNotNull { tx ->
            val score = abs((tx.amount - avg) / deviation)
            if (score > AI.ANOMALY_THRESHOLD) {
                Anomaly(
                    transaction = tx,
                    severity = when {
                        score > 3.0 -> Severity.HIGH
                        score > 2.5 -> Severity.MEDIUM
                        else -> Severity.LOW
                    },
                    reason = if (tx.amount > avg) "Neobvykle vysoká" else "Neobvykle nízká"
                )
            } else null
        }
    }

    private fun calculateVariance(values: List<Double>): Double {
        if (values.isEmpty()) return 0.0
        val mean = values.average()
        return values.map { (it - mean).pow(2) }.average()
    }

    private fun generateInsights(expenses: List<Transaction>, categories: List<CategoryData>): List<Insight> {
        val list = mutableListOf<Insight>()

        val top = categories.maxByOrNull { it.amount }
        top?.let {
            list.add(
                Insight(
                    title = "Největší výdaj",
                    message = "${it.category.name}: ${it.amount.toInt()} Kč",
                    type = InsightType.CATEGORY
                )
            )
        }

        val frequent = categories.maxByOrNull { it.count }
        frequent?.let {
            list.add(
                Insight(
                    title = "Nejčastější",
                    message = "${it.category.name}: ${it.count}x",
                    type = InsightType.FREQUENCY
                )
            )
        }

        val avg = if (expenses.isNotEmpty()) {
            expenses.sumOf { it.amount } / expenses.size
        } else 0.0

        list.add(
            Insight(
                title = "Průměr",
                message = "${avg.toInt()} Kč",
                type = InsightType.AVERAGE
            )
        )

        return list
    }

    private fun generateTip(transactions: List<Transaction>, average: Double, trend: String): String {
        return when (trend) {
            AI.INCREASING -> "Výdaje rostou. Průměr: ${average.toInt()} Kč"
            AI.DECREASING -> "Skvělé! Výdaje klesají"
            else -> "Stabilní. Průměr: ${average.toInt()} Kč"
        }
    }
}

data class Spending(
    val total: Double,
    val daily: Double,
    val categories: List<CategoryData>,
    val recommendations: List<Recommend>,
    val anomalies: List<Anomaly>,
    val insights: List<Insight>,
    val trend: String
)

data class CategoryData(
    val category: Category,
    val amount: Double,
    val percentage: Double,
    val count: Int,
    val average: Double,
    val trend: String
)

data class Recommend(
    val type: RecommendType,
    val title: String,
    val message: String,
    val priority: Int
)

enum class RecommendType {
    WARNING,
    INFO,
    SUGGESTION,
    TIP
}

data class Prediction(
    val predicted: Double,
    val confidence: Double,
    val analyzed: Int,
    val current: Double,
    val daily: Double,
    val trend: String,
    val min: Double,
    val max: Double
)

data class CategoryInsight(
    val category: Category,
    val total: Double,
    val average: Double,
    val frequency: Double,
    val trend: String,
    val tip: String
)

data class Anomaly(
    val transaction: Transaction,
    val severity: Severity,
    val reason: String
)

enum class Severity {
    LOW,
    MEDIUM,
    HIGH
}

data class Insight(
    val title: String,
    val message: String,
    val type: InsightType
)

enum class InsightType {
    CATEGORY,
    FREQUENCY,
    AVERAGE
}

class GrokChat(private val apiKey: String) {

    suspend fun askFinancialAdvice(
        question: String,
        context: Map<String, Any>
    ): GrokResponse {
        if (apiKey.isEmpty()) {
            return GrokResponse(
                success = false,
                message = AI.Grok.ERROR_NO_API_KEY,
                timestamp = System.currentTimeMillis()
            )
        }

        val contextString = buildContext(context)
        val fullPrompt = "$contextString\n\nOtázka: $question"

        return try {
            val response = callGrokAPI(fullPrompt)
            GrokResponse(
                success = true,
                message = response,
                timestamp = System.currentTimeMillis()
            )
        } catch (e: Exception) {
            GrokResponse(
                success = false,
                message = e.message ?: AI.Grok.ERROR_NETWORK,
                timestamp = System.currentTimeMillis()
            )
        }
    }

    suspend fun analyzeBudget(
        spent: Double,
        budget: Double,
        remaining: Double,
        days: Int
    ): GrokResponse {
        val context = mapOf(
            "budget" to budget,
            "spent" to spent,
            "remaining" to remaining,
            "days" to days
        )
        return askFinancialAdvice(AI.Grok.PROMPT_ANALYZE, context)
    }

    suspend fun getSavingsTips(categories: List<CategoryData>): GrokResponse {
        val topCategories = categories
            .sortedByDescending { it.amount }
            .take(3)
            .joinToString(", ") { "${it.category.name}: ${it.amount.toInt()} Kč" }

        val context = mapOf("top_categories" to topCategories)
        return askFinancialAdvice(AI.Grok.PROMPT_SAVINGS, context)
    }

    suspend fun predictSpending(prediction: Prediction): GrokResponse {
        val context = mapOf(
            "current" to prediction.current,
            "predicted" to prediction.predicted,
            "daily" to prediction.daily,
            "trend" to prediction.trend
        )
        return askFinancialAdvice(AI.Grok.PROMPT_PREDICT, context)
    }

    private fun buildContext(context: Map<String, Any>): String {
        return context.entries.joinToString("\n") { (key, value) ->
            when (key) {
                "budget" -> "${AI.Grok.CONTEXT_PREFIX} ${value} Kč"
                "spent" -> "${AI.Grok.CONTEXT_SPENT} ${value} Kč"
                "remaining" -> "${AI.Grok.CONTEXT_REMAINING} ${value} Kč"
                "days" -> "${value} ${AI.Grok.CONTEXT_DAYS}"
                else -> "$key: $value"
            }
        }
    }

    private suspend fun callGrokAPI(prompt: String): String {
        val requestBody = """
            {
                "model": "${AI.Grok.MODEL}",
                "messages": [
                    {
                        "role": "${AI.Grok.ROLE_SYSTEM}",
                        "content": "Jsi finanční poradce. Dávej stručné a praktické rady v češtině."
                    },
                    {
                        "role": "${AI.Grok.ROLE_USER}",
                        "content": "$prompt"
                    }
                ],
                "max_tokens": ${AI.Grok.MAX_TOKENS},
                "temperature": ${AI.Grok.TEMPERATURE}
            }
        """.trimIndent()

        return "Grok odpověď zde"
    }
}

data class GrokResponse(
    val success: Boolean,
    val message: String,
    val timestamp: Long
)

data class GrokMessage(
    val role: String,
    val content: String
)

data class GrokRequest(
    val model: String,
    val messages: List<GrokMessage>,
    val maxTokens: Int,
    val temperature: Double
)
